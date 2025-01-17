package yosolito;

import io.jsonwebtoken.Clock;

import java.util.Date;

public class MyClock implements Clock {
    @Override
    public Date now() {
        return new Date();
    }
}
