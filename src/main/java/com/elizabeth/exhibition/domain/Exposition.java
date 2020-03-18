package com.elizabeth.exhibition.domain;

import lombok.Getter;

@Getter
public class Exposition {
    private final Long id;
    private final String name;
    private final String about;
    private final String image;

    public Exposition(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.about = builder.about;
        this.image = builder.image;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private long id;
        private String name;
        private String about;
        private String image;

        private Builder() {}

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withAbout(String about) {
            this.about = about;
            return this;
        }

        public Builder withImage(String image) {
            this.image = image;
            return this;
        }

        public Exposition build() { return new Exposition(this); }
    }
}
