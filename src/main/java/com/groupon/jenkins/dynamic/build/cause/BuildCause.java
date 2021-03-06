/*
The MIT License (MIT)

Copyright (c) 2014, Groupon, Inc.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */
package com.groupon.jenkins.dynamic.build.cause;

import hudson.model.Cause;

import java.util.HashMap;
import java.util.Map;

import com.groupon.jenkins.github.GitBranch;

public abstract class BuildCause extends Cause {

    public static final BuildCause NULL_BUILD_CAUSE = new NullBuildCause();

    public abstract String getSha();

    public abstract String getBuildDescription();

    public abstract String getPusher();

    public abstract String getPullRequestNumber();

    public Map<String, String> getEnvVars() {
        Map<String, String> vars = new HashMap<String, String>();
        putIfNotNull(vars, "DOTCI_SHA", getSha());
        putIfNotNull(vars, "DOTCI_PUSHER", getPusher());
        putIfNotNull(vars, "DOTCI_PULL_REQUEST", getPullRequestNumber());
        return vars;
    }

    private static void putIfNotNull(Map<String, String> vars, String key, String value) {
        if (value != null) {
            vars.put(key, value);
        }
    }

    public abstract GitBranch getBranch();

    public abstract Iterable<GithubLogEntry> getChangeLogEntries();

}
