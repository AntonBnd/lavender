package by.lavender.pool;

import by.lavender.pool.exception.ConnectionException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class DBConnection {

    private static Logger logger = LogManager.getLogger(DBConnection.class);

    private static DBConnection instance;
    private static ReentrantLock lock = new ReentrantLock();
    private ArrayBlockingQueue<ConnectionImpl> connectionQueue;
    private static final int DEFAULT_POOL_SIZE = 10;
    private static AtomicBoolean poolAtomic = new AtomicBoolean(false);
    private String url;
    private String password;
    private String user;
    private int poolSize;

    private DBConnection() {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("src/main/resources/jdbc.properties")) {
            prop.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        password = prop.getProperty("jdbc.password");
        user = prop.getProperty("jdbc.user");
        url = prop.getProperty("jdbc.url");

        try {
            poolSize = Integer.parseInt(prop.getProperty("jdbc.poolSize"));
        } catch (NumberFormatException ex) {
            poolSize = DEFAULT_POOL_SIZE;
        }

        try {
            initPool();
        } catch (ConnectionException ex) {
            logger.fatal("Fatal error with connection: " + ex);
            throw new RuntimeException(ex);
        }

    }

    public static DBConnection getInstance() {
        if (!poolAtomic.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new DBConnection();
                    poolAtomic.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private void initPool() throws ConnectionException {
        connectionQueue = new ArrayBlockingQueue<>(poolSize);

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            int i = 0;
            while (i <= poolSize * 3) {
                Connection connection = DriverManager.getConnection(url, user, password);
                ConnectionImpl connectionImpl = new ConnectionImpl(connection);
                connectionQueue.offer(connectionImpl);
                i++;
            }
        } catch (SQLException e) {
            throw new ConnectionException("Init error", e);
        }
    }

    public ConnectionImpl getConnection() {
        ConnectionImpl connectionImpl = null;
        try {
            connectionImpl = connectionQueue.take();
        } catch (InterruptedException e) {
            logger.error("Get connection from pool error", e);
        }
        return connectionImpl;
    }

    void closeConnection (ConnectionImpl connectionImpl){
        connectionQueue.offer(connectionImpl);
    }

    public void destroyConnections () {
        ConnectionImpl connectionImpl;

        while ((connectionImpl = connectionQueue.poll()) != null) {
            try {
                connectionImpl.destroyConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}