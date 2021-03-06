/**
 * Logback: the reliable, generic, fast and flexible logging framework.
 * Copyright (C) 1999-2009, QOS.ch. All rights reserved.
 *
 * This program and the accompanying materials are dual-licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation
 *
 *   or (per the licensee's choosing)
 *
 * under the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation.
 */
package chapter10;

import java.util.List;
import java.util.Map;

import ch.qos.logback.core.joran.GenericConfigurator;
import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.joran.action.ImplicitAction;
import ch.qos.logback.core.joran.spi.Interpreter;
import ch.qos.logback.core.joran.spi.Pattern;
import ch.qos.logback.core.joran.spi.RuleStore;

/**
 * A minimal configurator extending GenericConfigurator.
 * 
 * @author Ceki G&uuml;c&uuml;
 *
 */
public class SimpleConfigurator extends GenericConfigurator {

  final Map<Pattern, Action> ruleMap;
  final List<ImplicitAction> iaList;

  public SimpleConfigurator(Map<Pattern, Action> ruleMap) {
    this(ruleMap, null);
  }

  public SimpleConfigurator(Map<Pattern, Action> ruleMap, List<ImplicitAction> iaList) {
    this.ruleMap = ruleMap;
    this.iaList = iaList;
  }

  @Override
  protected void addInstanceRules(RuleStore rs) {
    for (Pattern pattern : ruleMap.keySet()) {
      Action action = ruleMap.get(pattern);
      rs.addRule(pattern, action);
    }
  }

  @Override
  protected void addImplicitRules(Interpreter interpreter) {
    if(iaList == null) {
      return;
    }
    for (ImplicitAction ia : iaList) {
      interpreter.addImplicitAction(ia);
    }
  }

}
