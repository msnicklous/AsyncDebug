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
@WebServlet(urlPatterns="/Debug", asyncSupported=true)
public class DebugServlet1 extends HttpServlet {
   private static final long serialVersionUID = -755297662096400270L;

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
      PrintWriter writer = resp.getWriter();
      StringBuilder txt = new StringBuilder(128);
      txt.append("<h3>Async Debug 1</h3>");
      writer.append(txt.toString());
      resp.flushBuffer();
      
      AsyncContext actx = req.startAsync(req, resp);
      actx.dispatch("/WEB-INF/jsp/debug1.jsp");
      
   }
}
