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
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Scott Nicklous
 *
 */
@WebServlet(asyncSupported=true, urlPatterns = "/ltest")
public class ListenerTester extends HttpServlet {
   private static final long serialVersionUID = -354310331397841504L;
   private static final Logger LOGGER = Logger.getLogger(ListenerTester.class.getName());

   /**
    * 
    */
   public ListenerTester() {
   }
   
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
      String reps = req.getParameter("reps");
      String err = req.getParameter("err");
      String timeout = req.getParameter("timeout");
      String flush = req.getParameter("flush");
      
      resp.setContentType("text/html");
      PrintWriter writer = resp.getWriter();
      
      AsyncContext ctx = req.startAsync();
      ctx.setTimeout(2000);

      Integer ireps = (Integer) req.getAttribute("ireps");
      if (ireps == null) {
         writer.println("<h3>Async Servlet</h3>");
         writer.println("<p>This servlet starts async and then optionally times out or throws exception.</p>");
         writer.println("<p>Query string parameters:");
         writer.println("<br>reps=n - number of repetitions");
         writer.println("<br>err  - throw exception");
         writer.println("<br>flush - flush buffer before timeout or exception");
         writer.println("</p>");
         ireps = 0;
         TestedListener tl = new TestedListener();
         ctx.addListener(tl);
         if (reps != null) {
            try {
               ireps = Integer.parseInt(reps);
            } catch (Exception e) {}
         }
      }

      StringBuilder txt = new StringBuilder(128);
      txt.append("Doing iteration.");
      txt.append(" dispatch type: ").append(req.getDispatcherType().name());
      txt.append(", err: ").append((err != null) ? "not null" : "null");
      txt.append(", flush: ").append((flush != null) ? "not null" : "null");
      txt.append(", timeout: ").append((timeout != null) ? "not null" : "null");
      txt.append(", reps: ").append(reps);
      txt.append(", ireps: ").append(ireps);
      LOGGER.fine(txt.toString());
      writer.println("<p>" + txt.toString() + "</p>");
      
      if (ireps > 1) {
         ireps--;
         req.setAttribute("ireps", ireps);
         ctx.dispatch();
      } else {
         if (flush != null) {
            resp.flushBuffer();
         }
         if (err != null) {
            throw new ServletException("Hasta la vista, baby!");
         } else if (timeout == null) {
            ctx.complete();
         }
      }
      
   }

}
