package com.raj.shashi.couchbase;

import com.couchbase.client.core.diagnostics.PingResult;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.Scope;

public class CouchbaseConnector {

    private static Scope scope;

    public static Scope getScope(){

        if(scope!=null){
            return scope;
        }

        synchronized (CouchbaseConnector.class){

            if(scope==null){

                synchronized (CouchbaseConnector.class){
                    Cluster cluster = Cluster.connect("localhost", "root", "shashi");

                    PingResult  pingResult = cluster.ping();
                    System.out.print(pingResult.id());
                    Bucket bucket = cluster.bucket("user");

                    scope = bucket.defaultScope();
                }
            }

            return scope;
        }

    }

    public  static void main(String [] args){
        CouchbaseConnector ob = new CouchbaseConnector();
        Scope scope = ob.getScope();
        Collection collection = scope.collection("user");
    }
}
