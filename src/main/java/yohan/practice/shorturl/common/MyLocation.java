package yohan.practice.shorturl.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MyLocation {


    public static String location;

    @Value("${custom.location}")
    public void setLocation(String location) {
        this.location = location;
    }
}
