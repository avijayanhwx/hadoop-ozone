/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.hdds.upgrade;

import java.util.Optional;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import org.apache.hadoop.ozone.upgrade.LayoutFeature;

/**
 * List of HDDS Features.
 */
public enum HDDSLayoutFeature implements LayoutFeature {
  //////////////////////////////  //////////////////////////////
  INITIAL_VERSION(0, "Initial Layout Version"),
  DATANODE_SCHEMA_V2(1, "Datanode RocksDB Schema Version 2 (with column " +
      "families)");

  //////////////////////////////  //////////////////////////////

  private int layoutVersion;
  private String description;
  private HDDSUpgradeAction scmUpgradeAction;
  private HDDSUpgradeAction datanodeUpgradeAction;

  HDDSLayoutFeature(final int layoutVersion, String description) {
    this.layoutVersion = layoutVersion;
    this.description = description;
  }

  @SuppressFBWarnings("ME_ENUM_FIELD_SETTER")
  public void setSCMUpgradeAction(HDDSUpgradeAction scmAction) {
    this.scmUpgradeAction = scmAction;
  }

  @SuppressFBWarnings("ME_ENUM_FIELD_SETTER")
  public void setDataNodeUpgradeAction(HDDSUpgradeAction datanodeAction) {
    this.datanodeUpgradeAction = datanodeAction;
  }

  @Override
  public int layoutVersion() {
    return layoutVersion;
  }

  @Override
  public String description() {
    return description;
  }

  public Optional<? extends HDDSUpgradeAction> onFinalizeSCMAction() {
    return Optional.ofNullable(scmUpgradeAction);
  }

  public Optional<? extends HDDSUpgradeAction> onFinalizeDataNodeAction() {
    return Optional.ofNullable(datanodeUpgradeAction);
  }
}
