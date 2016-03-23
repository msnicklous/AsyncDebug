/*  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */


package basic.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletResponse;

/**
 * @author Scott Nicklous
 *
 */
public class TestedListener implements AsyncListener {
   private static final Logger LOGGER = Logger.getLogger(TestedListener.class.getName());

   /**
    * 
    */
   public TestedListener() {
   }

   /* (non-Javadoc)
    * @see javax.servlet.AsyncListener#onComplete(javax.servlet.AsyncEvent)
    */
   @Override
   public void onComplete(AsyncEvent evt) throws IOException {
      ServletResponse resp = evt.getAsyncContext().getResponse();
      resp.getWriter().write("<p>Async processing complete.</p>");
      Integer ireps = (Integer) evt.getAsyncContext().getRequest().getAttribute("ireps");
      LOGGER.fine("called. ireps: " + ireps);
   }

   /* (non-Javadoc)
    * @see javax.servlet.AsyncListener#onError(javax.servlet.AsyncEvent)
    */
   @Override
   public void onError(AsyncEvent evt) throws IOException {
      Integer ireps = (Integer) evt.getAsyncContext().getRequest().getAttribute("ireps");
      LOGGER.fine("called. ireps: " + ireps);
   }

   /* (non-Javadoc)
    * @see javax.servlet.AsyncListener#onStartAsync(javax.servlet.AsyncEvent)
    */
   @Override
   public void onStartAsync(AsyncEvent evt) throws IOException {
      Integer ireps = (Integer) evt.getAsyncContext().getRequest().getAttribute("ireps");
      LOGGER.fine("called. ireps: " + ireps + ", attaching myself again.");
      AsyncContext ctx = evt.getAsyncContext();
      ctx.addListener(this);
   }

   /* (non-Javadoc)
    * @see javax.servlet.AsyncListener#onTimeout(javax.servlet.AsyncEvent)
    */
   @Override
   public void onTimeout(AsyncEvent evt) throws IOException {
      ServletResponse resp = evt.getAsyncContext().getResponse();
      resp.getWriter().write("<p>timed out.</p>");
      Integer ireps = (Integer) evt.getAsyncContext().getRequest().getAttribute("ireps");
      LOGGER.fine("called. ireps: " + ireps);
   }

}
