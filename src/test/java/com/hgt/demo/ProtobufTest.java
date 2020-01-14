package com.hgt.demo;

import com.google.protobuf.InvalidProtocolBufferException;
import com.hgt.demo.protomodel.PersonModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Title: ProtobufTest
 * @Description:
 * @Author 柳强
 * @Date 2020/01/13/0013 19:28
 * @Version V0.1
 * @Modified By:
 * Copyright: Copyright (c) 2019
 * Company: 恒歌科技
 */
@SpringBootTest
public class ProtobufTest {

    @Test
    public void testN() throws InvalidProtocolBufferException {
        PersonModel.Person.Builder builder = PersonModel.Person.newBuilder();
        builder.setId(1);
        builder.setName("liuqiang");
        builder.setEmail("liuqiang@henggetec.com");

        PersonModel.Person person = builder.build();
        System.out.println("before:" + person);

        System.out.println("----------Person Byte----------:");
        for (byte b : person.toByteArray()) {
            System.out.print(b);
        }
        System.out.println("------------------------------");

        byte[] byteArray = person.toByteArray();
        PersonModel.Person p2 = PersonModel.Person.parseFrom(byteArray);
        System.out.println("after id:" + p2.getId());
        System.out.println("after name:" + p2.getName());
        System.out.println("after email:" + p2.getEmail());
    }

}
