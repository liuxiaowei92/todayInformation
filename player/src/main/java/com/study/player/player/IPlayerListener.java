package com.study.player.player;

import com.study.player.state.PlayerState;

/**
 * @authour lxw
 * @function
 * @date 2019/11/18
 */
public interface IPlayerListener {
    void playerStateChanged(PlayerState state);
}
