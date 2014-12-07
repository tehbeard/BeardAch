/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tehbeard.beardach;

import org.spongepowered.api.text.message.Message;
import org.spongepowered.api.util.command.source.ConsoleSource;

/**
 *
 * @author James
 */
public class FakeConsole implements ConsoleSource {

    @Override
    public void sendMessage(String... strings) {
    }

    @Override
    public void sendMessage(Message<?>... msgs) {
    }

    @Override
    public void sendMessage(Iterable<Message<?>>... itrbls) {

    }
    
}
