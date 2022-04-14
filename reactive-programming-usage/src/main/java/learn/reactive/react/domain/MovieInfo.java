package learn.reactive.react.domain;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieInfo {
    private String movieInfoId;
    private String name;
    private List<String> cast;
    private Integer year;
    private LocalDate release_date;
}
