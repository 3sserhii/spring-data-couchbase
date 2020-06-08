package ssserhii.spring.data.composite;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.List;
import java.util.Objects;

@Document
public class User {

    @Id
    private final String id;

    @Field(name = "fnames")
    private final List<String> firstnames;

    @Field(name = "child")
    private final List<Child> children;

    @PersistenceConstructor
    public User(String id, List<String> firstnames, List<Child> children) {
        this.id = id;
        this.firstnames = firstnames;
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public List<String> getFirstnames() {
        return firstnames;
    }

    public List<Child> getChildren() {
        return children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(firstnames, user.firstnames) &&
                Objects.equals(children, user.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstnames, children);
    }

    static class Child {
        private final String name;
        private final int age;

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @PersistenceConstructor
        public Child(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Child child = (Child) o;
            return age == child.age &&
                    Objects.equals(name, child.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }
    }

}