/**
Copyright 2010 University of South Florida

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

**/
package io;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import object.Session;
import object.Stop;

public class OsmPrinter {

    public static final String DEFAULT_API_VERSION = "0.6";
    private final String APPLICATION_CREATOR_KEY = "source";
    private final String APPLICATION_CREATOR_NAME = "GO_Sync";

    public String header() {
        String s = "<?xml version='1.0' encoding='UTF-8'?>\n" +
                "<osm version='" + DEFAULT_API_VERSION + "generator='"+Session.getUserName()+"'>\n";
        return s;
    }
    public String footer() {
        return "</osm>";
    }
    public String osmChangeCreate(){
        return "<osmChange version='0.6'" + " generator='"+Session.getUserName()+"'>\n" +
                "<create>\n";
    }
    public String osmChangeModify(){
        return "</create>\n" +
                "<modify>\n";
    }
    
    public String osmChangeDelete(){
        return "</modify>\n" +
                "<delete>\n";
    }

    public String osmChangeDeleteClose(){
        return "</delete>\n" +
                "</osmChange>\n";
    }

    public String writeChangeSet() {
        return "<changeset>\n" +
                "<tag k='created_by' v='"+Session.getUserName()+"'/>\n" +
                "<tag k='comment' v='"+Session.getChangeSetComment()+"'/>\n" +
                "</changeset>\n";
    }

    public String writeDeleteNode(String nodeID, String changeSetID, String version) {
        return "<node id='"+nodeID+"' changeset='"+ changeSetID + "' version='"+ version +"' />\n";
    }

    public String writeBusStop(String changeSetID, String lat, String lon) {
        return "<node changeset='" + changeSetID+ "' lat='" + lat + "' lon='" + lon + "'>\n" +
                "<tag k='highway' v='bus_stop'/>\n" +
                "</node>";
    }

    public String writeBusStop(String changeSetID, String nodeID, Stop st) {
        String text="";
        Stop s = new Stop(st);
        // if modify, we need version number
        if(st.getTag("version")!=null) {
            text += "<node changeset='" + changeSetID + "' id='" + nodeID
                    + "' lat='" + st.getLat() + "' lon='" + st.getLon()
                    + "' version='"+st.getTag("version") + "'>\n";
            s.removeTag("version");
        }
        // mainly for create new node
        else {
            text += "<node changeset='" + changeSetID + "' id='" + nodeID
                    + "' lat='" + st.getLat() + "' lon='" + st.getLon() + "'>\n";
            text += "<tag k='"+APPLICATION_CREATOR_KEY+"' v='"+APPLICATION_CREATOR_NAME+"' />\n";
        }
        HashSet<String> keys = new HashSet<String>(s.keySet().size());
        keys.addAll(s.keySet());
        Iterator it = keys.iterator();
        while (it.hasNext()){
            String k = (String) it.next();
            if (!s.getTag(k).equals("none")) {
                text += "<tag k='"+k+"' v='"+s.getTag(k)+"' />\n";
            }
        }
        text += "</node>\n";
        return text;
    }
}