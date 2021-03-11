package simbirsoft.pearser.task.models;

import javax.persistence.*;

@Entity
@Table(name = "words")
public class WordData {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", updatable=false, nullable=false)
    private int  id;
    @Column(name = "word")
    private String word;
    @Column(name = "count")
    private int count;
    @Column(name = "url")
    private String url;

    public WordData(int id, String word, int count, String url) {
        this.id = id;
        this.word = word;
        this.count = count;
        this.url = url;
    }

    public WordData() {

    }

    public int getId() { return id; }

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }

    public String getUrl() {
        return url;
    }

    public void setId(int id) { this.id = id; }

    public void setWord(String word) {
        this.word = word;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setUrl(String url) { this.url = url; }
}
