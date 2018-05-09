package com.xppcoder.launcher;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;

import java.io.File;
import java.io.IOException;

public class TigerMain {

    public static void main(String[] args) throws IOException {

        //Source source =Source.newBuilder("tiger",new File(args[0])).build();

        Context c = Context.newBuilder("tiger").build();

        c.eval("tiger", "print(1)");
    }
}
