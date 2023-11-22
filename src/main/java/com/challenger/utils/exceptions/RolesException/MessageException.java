package com.challenger.utils.exceptions.RolesException;

public enum MessageException {
    CLOSED_SESSION{
        @Override
        String getMessageError(){
            return "This session was closed!";
        }
    },
    SESSION_NOT_FOUND{
        @Override
        String getMessageError(){
            return "Session not found!";
        }
    },
    SESSION_ALREADY_EXISTS{
        @Override
        String getMessageError(){
            return "Session already exists with the identifier!";
        }
    },
    SCHEDULE_NOT_FOUND{
        @Override
        String getMessageError(){
            return "Schedule not found!";
        }
    },
    REGISTERED_VOTE{
        @Override
        String getMessageError(){
            return "You already voted!";
        }
    },
    USER_NOT_FOUND{
        @Override
        String getMessageError(){
            return "User not found!";
        }
    };

    abstract String getMessageError();
}
