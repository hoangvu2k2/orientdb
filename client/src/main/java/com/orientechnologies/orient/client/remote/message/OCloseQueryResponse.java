package com.orientechnologies.orient.client.remote.message;

import com.orientechnologies.orient.client.remote.OBinaryResponse;
import com.orientechnologies.orient.client.remote.OStorageRemoteSession;
import com.orientechnologies.orient.enterprise.channel.binary.OChannelDataInput;
import com.orientechnologies.orient.enterprise.channel.binary.OChannelDataOutput;

import java.io.IOException;

/**
 * Created by luigidellaquila on 01/12/16.
 */
public class OCloseQueryResponse implements OBinaryResponse {

  public OCloseQueryResponse() {
  }

  @Override public void write(OChannelDataOutput channel, int protocolVersion, String recordSerializer) throws IOException {
  }

  @Override public void read(OChannelDataInput network, OStorageRemoteSession session) throws IOException {
  }
}