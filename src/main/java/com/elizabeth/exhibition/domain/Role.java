package com.elizabeth.exhibition.domain;

import lombok.Getter;

@Getter
public class Role {
    private final Integer id;
    private final String name;
    private final String description;

    public Role(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer id;
        private String name;
        private String description;

        private Builder() {
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Role build() { return new Role(this); }
    }
}
