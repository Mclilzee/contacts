package contacts;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@Configurable
public class BeansContainer {

    @Bean
    public Scanner getScanner() {
        return new Scanner(System.in);
    }
}
