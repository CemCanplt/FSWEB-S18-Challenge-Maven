package com.workintech.fswebs18challengemaven.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data // Getter, Setter, toString, equals, hashCode metotlarını otomatik ekler
@NoArgsConstructor // Argümansız constructor ekler
@AllArgsConstructor // Tüm argümanları içeren constructor ekler
@Entity // Bu sınıfın bir JPA varlığı (entity) olduğunu belirtir
@Table(name = "card", schema = "public") // Veritabanındaki tablo adını ve şemasını belirtir
public class Card {

    @Id // Primary key olduğunu belirtir
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID'nin veritabanı tarafından otomatik oluşturulacağını belirtir
    private Long id;

    @Column(name = "value") // Sütun adını belirtir (isteğe bağlı, varsayılan değişken adı kullanılır)
    private Integer value;

    @Column(name = "type")
    @Enumerated(EnumType.STRING) // Enum değerlerini String olarak veritabanına kaydeder
    private Type type;

    @Column(name = "color")
    @Enumerated(EnumType.STRING) // Enum değerlerini String olarak veritabanına kaydeder
    private Color color;

    // Lombok @Data annotasyonu sayesinde getter, setter, toString vb. metotlar buraya eklenmiş gibi davranır
}