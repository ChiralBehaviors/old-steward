/**
 * Copyright (c) 2015 Chiral Behaviors, LLC, all rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chiralbehaviors.steward.workspace;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.junit.Test;

import com.chiralbehaviors.CoRE.workspace.dsl.WorkspaceLexer;
import com.chiralbehaviors.CoRE.workspace.dsl.WorkspaceParser;


/**
 * @author hparry
 *
 */
public class WorkspaceParserTest {
    
    @Test
    public void testParse() throws Exception {
        WorkspaceLexer l = new WorkspaceLexer(
                                              new ANTLRInputStream(
                                                                   getClass().getResourceAsStream("/steward-workspace.wsp")));
        WorkspaceParser p = new WorkspaceParser(new CommonTokenStream(l));
        p.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer,
                                    Object offendingSymbol, int line,
                                    int charPositionInLine, String msg,
                                    RecognitionException e) {
                throw new IllegalStateException("failed to parse at line "
                                                + line + " due to " + msg, e);
            }
        });
        p.workspace();
    }
}
