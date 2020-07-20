package by.lavender.command;

public enum CommandEnum {
    GET_COUNTRIES {
        {
            this.command = new ViewAllCountriesCommand();
        }
    },
    COUNTRY {
        {
            this.command = new ToursOfCountryCommand();
        }
    },
    GET_RESORTS {
        {
            this.command = new ViewAllResortsCommand();
        }
    },
    RESORT {
        {
            this.command = new ToursOfResortCommand();
        }
    },
    REGISTRATE {
        {
            this.command = new RegistrateCommand();
        }
    },
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    CHANGE_LANGUAGE {
        {
            this.command = new ChangeLanguageCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    ORDER_TOUR {
        {
            this.command = new OrderTourCommand();
        }
    },
    GET_USER_ORDERS {
        {
            this.command = new GetUserOrdersCommand();
        }
    },
    CANCEL_ORDER {
        {
            this.command = new CancelOrderCommand();
        }
    },
    VIEW_ALL_ORDERS {
        {
            this.command = new ViewAllOrdersCommand();
        }
    },
    PROCESS_ORDER {
        {
            this.command = new ProcessOrderCommand();
        }
    },
    PAY {
        {
            this.command = new PayCommand();
        }
    },
    ADD_TOUR {
        {
            this.command = new AddTourCommand();
        }
    },
    GET_ADD_DATA {
        {
            this.command = new GetAddDataCommand();
        }
    },
    GET_UPDATE_DATA {
        {
            this.command = new GetUpdateDataCommand();
        }
    },
    UPDATE_TOUR {
        {
            this.command = new UpdateTourCommand();
        }
    },
    GET_BURNING_TOURS {
        {
            this.command = new GetBurningToursCommand();
        }
    },
    DELETE_TOUR {
        {
            this.command = new DeleteTourCommand();
        }
    };

    ActionCommand command;
    public ActionCommand getCurrentCommand(){
        return command;
    }
}
