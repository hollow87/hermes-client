/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermes.events;

import com.hermes.common.HChannel;

/**
 *
 * @author joaquin
 */
public interface ChannelListClickedEvent
{
    public void channelListClick(HChannel channel);
    
    public void channelDownloaded(int count);
}
