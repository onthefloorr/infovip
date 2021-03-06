/*
 * Copyright (C) 2016 attila
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.infovip.core.basic.tags.elasticsearch;

import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * ESEntityForeach helps to iterate the entities.
 *
 * @example
 * <esentity:entity entity="${iterableObject}" var="testPost">
 * <esentity:foreach current="testPost">
 * UserName : <esentity:out field="userName" /><br>
 * ID : <esentity:out field="id" /> <br>
 * CreationTime: <esentity:out field="creationTime" /><br>
 * </esentity:foreach>
 * </esentity:entity>
 *
 * @author attila
 */
public class ESEntityForeach extends BodyTagSupport {

    private String current;

    private Object iterableEntityObject;

    private Iterator currentIterator;

    private Object entityObject;

    public ESEntityForeach() {
        super();
    }

    @Override
    public int doStartTag() throws JspException {
        if (current != null) {
            iterableEntityObject = pageContext.getAttribute(current);
        } else if (getParent() instanceof ESEntity) {
            iterableEntityObject = ((ESEntity) getParent()).getEntity();
        } else {
            throw new UnsupportedOperationException("Entity is not defined.");
        }

        if (iterableEntityObject instanceof Iterable) {
            currentIterator = ((Iterable) iterableEntityObject).iterator();
        } else {
            throw new UnsupportedOperationException("Iterable object is expected.");
        }

        return EVAL_BODY_BUFFERED;
    }

    @Override
    public int doAfterBody() throws JspException {
        try {
            JspWriter out = null;

            if (bodyContent == null) {
                out = pageContext.getOut();
            } else {
                out = bodyContent.getEnclosingWriter();
            }

            if (iterableEntityObject instanceof Iterable) {
                if (currentIterator.hasNext()) {
                    entityObject = currentIterator.next();
                    return EVAL_BODY_AGAIN;
                } else {
                	out.print(bodyContent.getString());
                	bodyContent.clear();
                    return SKIP_BODY;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ESEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        current = null;
        return super.doEndTag(); //To change body of generated methods, choose Tools | Templates.
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public Object getEntityObject() {
        return entityObject;
    }

}
