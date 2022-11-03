package com.model;

import javax.persistence.*;

@Entity
@Table(name = "blogs_security")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title, summary, dates, people;

    @Column(columnDefinition = "long")
    private String content;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Blog() {
    }

    public Blog(Long id, String title, String summary, String dates, String people, String content) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.dates = dates;
        this.people = people;
        this.content = content;
    }

    public Blog(String title, String summary, String dates, String people, String content) {
        this.title = title;
        this.summary = summary;
        this.dates = dates;
        this.people = people;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String date) {
        this.dates = date;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
