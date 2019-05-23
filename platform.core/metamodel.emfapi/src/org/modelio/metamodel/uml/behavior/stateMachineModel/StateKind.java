/* 
 * Copyright 2013-2018 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.metamodel.uml.behavior.stateMachineModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * StateKind v0.0.9054
 * 
 * 
 * null
 */
@objid ("000d61fa-91e1-1f74-804b-001ec947cd2a")
public enum StateKind {
    INITIALSTATE (0, "InitialState", "InitialState"),
    DEEPHISTORYSTATE (1, "DeepHistoryState", "DeepHistoryState"),
    SHALLOWHISTORYSTATE (2, "ShallowHistoryState", "ShallowHistoryState"),
    JOINSTATE (3, "JoinState", "JoinState"),
    FORKSTATE (4, "ForkState", "ForkState"),
    BRANCHSTATE (5, "BranchState", "BranchState"),
    OLDFINALSTATE (6, "OldFinalState", "OldFinalState"),
    SIGNALRECEIPTSTATE (7, "SignalReceiptState", "SignalReceiptState"),
    SIGNALSENDINGSTATE (8, "SignalSendingState", "SignalSendingState"),
    SYNCHRONIZATIONSTATE (9, "SynchronizationState", "SynchronizationState");

public static final int INITIALSTATE_VALUE = 0;
public static final int DEEPHISTORYSTATE_VALUE = 1;
public static final int SHALLOWHISTORYSTATE_VALUE = 2;
public static final int JOINSTATE_VALUE = 3;
public static final int FORKSTATE_VALUE = 4;
public static final int BRANCHSTATE_VALUE = 5;
public static final int OLDFINALSTATE_VALUE = 6;
public static final int SIGNALRECEIPTSTATE_VALUE = 7;
public static final int SIGNALSENDINGSTATE_VALUE = 8;
public static final int SYNCHRONIZATIONSTATE_VALUE = 9;
private static final StateKind[] VALUES_ARRAY =
new StateKind[] {
INITIALSTATE,
DEEPHISTORYSTATE,
SHALLOWHISTORYSTATE,
JOINSTATE,
FORKSTATE,
BRANCHSTATE,
OLDFINALSTATE,
SIGNALRECEIPTSTATE,
SIGNALSENDINGSTATE,
SYNCHRONIZATIONSTATE,
};
public static final List<StateKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));
public static StateKind get(String literal) {
  for (int i = 0; i < VALUES_ARRAY.length; ++i) {
    StateKind result = VALUES_ARRAY[i];
    if (result.toString().equals(literal)) {
       return result;
    }
  }
  return null;
}
public static StateKind getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
StateKind result = VALUES_ARRAY[i];
if (result.getName().equals(name)) {
return result;
}
}
return null;
}
public static StateKind get(int value) {
    switch (value) {
        case INITIALSTATE_VALUE: return INITIALSTATE;
        case DEEPHISTORYSTATE_VALUE: return DEEPHISTORYSTATE;
        case SHALLOWHISTORYSTATE_VALUE: return SHALLOWHISTORYSTATE;
        case JOINSTATE_VALUE: return JOINSTATE;
        case FORKSTATE_VALUE: return FORKSTATE;
        case BRANCHSTATE_VALUE: return BRANCHSTATE;
        case OLDFINALSTATE_VALUE: return OLDFINALSTATE;
        case SIGNALRECEIPTSTATE_VALUE: return SIGNALRECEIPTSTATE;
        case SIGNALSENDINGSTATE_VALUE: return SIGNALSENDINGSTATE;
        case SYNCHRONIZATIONSTATE_VALUE: return SYNCHRONIZATIONSTATE;
    }
    return null;
}
private final int value;
private final String name;
private final String literal;
private StateKind(int value, String name, String literal) {
  this.value = value;
  this.name = name;
  this.literal = literal;
}
public int getValue() {
   return value;
}
 public String getName() {
    return name;
}
public String getLiteral() {
  return literal;
}
 @Override
public String toString() {
   return literal;
}
}
