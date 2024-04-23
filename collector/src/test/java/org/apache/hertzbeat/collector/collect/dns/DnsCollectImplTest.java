/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hertzbeat.collector.collect.dns;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.hertzbeat.common.entity.job.Metrics;
import org.apache.hertzbeat.common.entity.job.protocol.DnsProtocol;
import org.apache.hertzbeat.common.entity.message.CollectRep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.xbill.DNS.Header;
import org.xbill.DNS.Message;
import org.xbill.DNS.Resolver;


/**
 * Test case for {@link DnsCollectImpl}
 *
 */
@ExtendWith(MockitoExtension.class)
public class DnsCollectImplTest {
    private DnsProtocol dnsProtocol;
    @InjectMocks
    private DnsCollectImpl dnsCollect;

    @Mock
    private Resolver res;

    @Mock
    private Message response;

    @Mock
    private Header header;

    @BeforeEach
    public void setup() {
        dnsCollect = new DnsCollectImpl();
        dnsProtocol = DnsProtocol.builder()
                .dnsServerIP("61.128.130.232")
                .address("www.baidu.com")
                .tcp("true")
                .port("53")
                .timeout("1000")
                .build();
    }

    @Test
    public void testCollect() {
        CollectRep.MetricsData.Builder builder = CollectRep.MetricsData.newBuilder();
        List<String> aliasField = new ArrayList<>();
        aliasField.add("section0");
        long monitorId = 666;
        String app = "testDNS";
        Metrics metrics = new Metrics();
        metrics.setName("additional");
        metrics.setDns(dnsProtocol);
        metrics.setAliasFields(aliasField);

        dnsCollect.collect(builder, monitorId, app, metrics);
        for (CollectRep.ValueRow valueRow : builder.getValuesList()) {
            System.out.println(valueRow.getColumns(0));
            System.out.println(valueRow.getColumns(1));
        }
    }

    @Test
    public void testHeaderTest() throws IOException {
        DnsProtocol dnsProtocol = DnsProtocol.builder()
                .dnsServerIP("8.8.8.8")
                .address("www.google.com")
                .tcp("true")
                .port("53")
                .timeout("1000")
                .build();
        CollectRep.MetricsData.Builder builder = CollectRep.MetricsData.newBuilder();
        List<String> aliasField = new ArrayList<>();
        aliasField.add("responseTime");
        aliasField.add("opcode");
        long monitorId = 666;
        String app = "testDNS";
        Metrics metrics = new Metrics();
        metrics.setName("header");
        metrics.setDns(dnsProtocol);
        metrics.setAliasFields(aliasField);

        Mockito.when(res.send(Mockito.any())).thenReturn(response);
        Mockito.when(response.getHeader()).thenReturn(header);
        Mockito.when(header.getOpcode()).thenReturn(1);

        dnsCollect.collect(builder, monitorId, app, metrics);
        for (CollectRep.ValueRow valueRow : builder.getValuesList()) {
            System.out.println(valueRow.getColumns(0));
            System.out.println(valueRow.getColumns(1));
        }
    }
}
