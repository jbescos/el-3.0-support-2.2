/*
 * Copyright (c) 1997, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package com.sun.el;

import java.io.Externalizable;
import java.io.IOException;
import javax.el.ELContext;
import javax.el.ELException;
import javax.el.PropertyNotWritableException;

import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.el.ValueExpression;

import com.sun.el.lang.ELSupport;
import com.sun.el.util.MessageFactory;
import com.sun.el.util.ReflectionUtil;

public final class ValueExpressionLiteral extends ValueExpression implements
        Externalizable {

    private static final long serialVersionUID = 1L;

    private Object value;

    private Class expectedType;

    public ValueExpressionLiteral() {
        super();
    }
    
    public ValueExpressionLiteral(Object value, Class expectedType) {
        this.value = value;
        this.expectedType = expectedType;
    }

    public Object getValue(ELContext context) {
        if (this.expectedType != null) {
            try {
                return context.convertToType(this.value, this.expectedType);
            } catch (IllegalArgumentException ex) {
                throw new ELException(ex);
            }
        }
        return this.value;
    }

    public void setValue(ELContext context, Object value) {
        throw new PropertyNotWritableException(MessageFactory.get(
                "error.value.literal.write", this.value));
    }

    public boolean isReadOnly(ELContext context) {
        return true;
    }

    public Class getType(ELContext context) {
        return (this.value != null) ? this.value.getClass() : null;
    }

    public Class getExpectedType() {
        return this.expectedType;
    }

    public String getExpressionString() {
        return (this.value != null) ? this.value.toString() : null;
    }

    public boolean equals(Object obj) {
        return (obj instanceof ValueExpressionLiteral && this
                .equals((ValueExpressionLiteral) obj));
    }

    public boolean equals(ValueExpressionLiteral ve) {
        return (ve != null && (this.value != null && ve.value != null && (this.value == ve.value || this.value
                .equals(ve.value))));
    }

    public int hashCode() {
        return (this.value != null) ? this.value.hashCode() : 0;
    }

    public boolean isLiteralText() {
        return true;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.value);
        out.writeUTF((this.expectedType != null) ? this.expectedType.getName()
                : "");
    }

    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {
        this.value = in.readObject();
        String type = in.readUTF();
        if (!"".equals(type)) {
            this.expectedType = ReflectionUtil.forName(type);
        }
    }
}
