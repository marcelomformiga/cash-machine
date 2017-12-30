package com.gsw.cashmachine.domain.cashmachine;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by eduardo on 29/12/17.
 */
@Data
public class Cash implements Serializable {

    private static final long serialVersionUID = 2353528370345499815L;

    private String username;
}
