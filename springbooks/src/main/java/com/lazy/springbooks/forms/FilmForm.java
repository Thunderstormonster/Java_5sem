package com.lazy.springbooks.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmForm {
    private int id;
    private String name;
    private String director;
}
