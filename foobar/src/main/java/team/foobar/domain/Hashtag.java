package team.foobar.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Hashtag {
    @Id @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String value;

    private Hashtag(String value) {
        this.value = value;
    }

    public static Hashtag make(String value) {
        return new Hashtag(value);
    }
}
