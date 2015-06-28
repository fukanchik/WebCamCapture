package com.jsjrobotics.server;

import java.net.Socket;

public interface ConnectedListener {
    public void connectionInitiated(int clientIndex);
}
