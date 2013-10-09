/*
 * Copyright 2010-2012 Luca Garulli (l.garulli--at--orientechnologies.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.orientechnologies.orient.core.storage.impl.local.paginated.wal;

import com.orientechnologies.common.log.OLogManager;
import com.orientechnologies.orient.core.storage.OCluster;
import com.orientechnologies.orient.core.storage.impl.local.paginated.OPaginatedCluster;
import com.orientechnologies.orient.core.storage.impl.local.paginated.wal.depricated.OPaginatedWithoutRidReuseCluster;

/**
 * @author Andrey Lomakin <a href="mailto:lomakin.andrey@gmail.com">Andrey Lomakin</a>
 * @since 10/8/13
 */
public class OPaginatedClusterFactory {
  public static final OPaginatedClusterFactory INSTANCE = new OPaginatedClusterFactory();

  public OCluster createCluster(int configurationVersion) {
    if (configurationVersion >= 0 && configurationVersion < 6) {
      OLogManager.instance().error(
          this,
          "You use deprecated version of storage cluster, "
              + "this version is not supported in current implementation. Please do export/import or recreate database.");
      return new OPaginatedWithoutRidReuseCluster();
    }

    return new OPaginatedCluster();
  }
}
