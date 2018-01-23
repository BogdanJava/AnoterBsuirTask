package com.bsuir.model;

import lombok.*;

import java.sql.Blob;

/**
 * 1){@link Data} @Data - аннотация, которая динамически создаёт геттеры и сеттеры для нашего класса
 * 2) Остальные аннотации генерируют соответствующие их названиям аспекты pojo(Plain Old Java Object)-объекта
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"name"})
@ToString
public class Fowl {
    private int id;
    private String name;
    private String description;
    private int number;
}
