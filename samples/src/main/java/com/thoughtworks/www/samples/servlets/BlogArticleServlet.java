package com.thoughtworks.www.samples.servlets;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import java.io.IOException;

// TODO: How to connect to real page with cq:Page or blog:Article ?

@SlingServlet(resourceTypes = "blog/Article", selectors = "view", extensions = "json", methods = "GET")
public class BlogArticleServlet extends SlingSafeMethodsServlet {

    private static Logger LOG = LoggerFactory.getLogger(BlogArticleServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        try {
            JSONObject jsonOut = new JSONObject();

            if (request.getResource().adaptTo(Node.class) == null) {
                // every adaptTo() can return null, so let's handle that case here
                // although it's very unlikely
                LOG.error("cannot adapt resource {} to a node", request.getResource().getPath());
                printEmptyJSON(response);
                return;
            }

            JSONObject articleDetails = new JSONObject();
            articleDetails.put("path", "test-path");
            articleDetails.put("mimetype", "test-mimetype");
            articleDetails.put("title", "test-title");
            jsonOut.put("article", articleDetails);

            response.getOutputStream().print(jsonOut.toString(2));

        } catch (JSONException e) {
            LOG.error("Cannot serialize JSON", e);
            printEmptyJSON(response);
        }
    }

    private void printEmptyJSON(SlingHttpServletResponse response) throws IOException {
        response.getOutputStream().print(new JSONObject().toString());
    }

}
