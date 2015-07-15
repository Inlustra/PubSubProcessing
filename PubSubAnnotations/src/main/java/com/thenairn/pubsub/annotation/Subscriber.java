/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thenairn.pubsub.annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author thomas
 */
@Inherited
@Retention(RetentionPolicy.SOURCE)
public @interface Subscriber {
    Class<? extends Enum> value();
}
