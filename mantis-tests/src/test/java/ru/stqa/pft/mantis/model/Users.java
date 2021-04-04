package ru.stqa.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Users extends ForwardingSet<UserData> {

    private Set<UserData> delegate;

    public Users(Collection<UserData> contact) {
        this.delegate = new HashSet<UserData>(contact);
    }

    public Users() {
        this.delegate = new HashSet<UserData>();
    }

    @Override
    protected Set<UserData> delegate() {
        return delegate;
    }
}