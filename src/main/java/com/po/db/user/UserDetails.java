package com.po.db.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "user_details")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_user_details_user_id_user")
    )
    private User user;
    
    @Column(name = "address", length = 50, nullable = false)
    @ColumnDefault("NULL")
    private String address;
    
    @Column(name = "city", length = 50, nullable = false)
    @ColumnDefault("NULL")
    private String city;
    
    @Column(name = "phone_number", length = 12, nullable = false)
    @ColumnDefault("NULL")
    private String phoneNumber;

    @Column(name = "birth_date", nullable = false)
    @ColumnDefault("NULL")
    private LocalDate birthDate;
}
