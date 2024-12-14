package whocraft.tardis_refined.common.network;

import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.network.messages.*;
import whocraft.tardis_refined.common.network.messages.hums.C2SChangeHum;
import whocraft.tardis_refined.common.network.messages.player.S2CResetPostShellView;
import whocraft.tardis_refined.common.network.messages.player.C2SExitTardisView;
import whocraft.tardis_refined.common.network.messages.player.C2SBeginShellView;
import whocraft.tardis_refined.common.network.messages.sync.S2CSyncTardisPlayerView;
import whocraft.tardis_refined.common.network.messages.screens.C2SRequestShellSelection;
import whocraft.tardis_refined.common.network.messages.screens.S2COpenMonitor;
import whocraft.tardis_refined.common.network.messages.screens.S2COpenShellSelection;
import whocraft.tardis_refined.common.network.messages.sync.*;
import whocraft.tardis_refined.common.network.messages.upgrades.S2CDisplayUpgradeScreen;
import whocraft.tardis_refined.common.network.messages.upgrades.C2SUnlockUpgrade;
import whocraft.tardis_refined.common.network.messages.waypoints.*;

public class TardisNetwork {

    public static final NetworkManager NETWORK = NetworkManager.get();

    public static void init() {
        // S2C Messages
        NETWORK.registerS2C(S2CSyncLevelList.TYPE, S2CSyncLevelList.STREAM_CODEC, (value, context) -> value.receive(value, context));
        NETWORK.registerS2C(S2CSyncTardisClientData.TYPE, S2CSyncTardisClientData.STREAM_CODEC, (value, context) -> value.receive(value, context));
        NETWORK.registerS2C(S2COpenMonitor.TYPE, S2COpenMonitor.STREAM_CODEC, (value, context) -> value.receive(value, context));
        NETWORK.registerS2C(S2COpenShellSelection.TYPE, S2COpenShellSelection.STREAM_CODEC, (value, context) -> value.receive(value, context));
        NETWORK.registerS2C(S2CSyncConsolePatterns.TYPE, S2CSyncConsolePatterns.STREAM_CODEC, (value, context) -> value.receive(value, context));
        NETWORK.registerS2C(S2CSyncShellPatterns.TYPE, S2CSyncShellPatterns.STREAM_CODEC, (value, context) -> value.receive(value, context));
        NETWORK.registerS2C(S2CSyncDesktops.TYPE, S2CSyncDesktops.STREAM_CODEC, (value, context) -> value.receive(value, context));
        NETWORK.registerS2C(S2COpenEditCoordinatesDisplayMessage.TYPE, S2COpenEditCoordinatesDisplayMessage.STREAM_CODEC, (value, context) -> value.receive(value, context));
        NETWORK.registerS2C(S2CWaypointsListScreen.TYPE, S2CWaypointsListScreen.STREAM_CODEC, (value, context) -> value.receive(value, context));
        NETWORK.registerS2C(S2COpenCoordinatesDisplayMessage.TYPE, S2COpenCoordinatesDisplayMessage.STREAM_CODEC, (value, context) -> value.receive(value, context));
        NETWORK.registerS2C(S2CSyncHums.TYPE, S2CSyncHums.STREAM_CODEC, (value, context) -> value.receive(value, context));
        NETWORK.registerS2C(S2CDisplayUpgradeScreen.TYPE, S2CDisplayUpgradeScreen.STREAM_CODEC, (value, context) -> value.receive(value, context));
        NETWORK.registerS2C(S2CSyncTardisPlayerView.TYPE, S2CSyncTardisPlayerView.STREAM_CODEC, (value, context) -> value.receive(value, context));
        NETWORK.registerS2C(S2CResetPostShellView.TYPE, S2CResetPostShellView.STREAM_CODEC, (value, context) -> value.receive(value, context));

        // C2S Messages
        NETWORK.registerS2C(C2SChangeShell.TYPE, C2SChangeShell.STREAM_CODEC, (value, context) -> value.receive(value, context));
        NETWORK.registerS2C(C2SChangeVortex.TYPE, C2SChangeVortex.STREAM_CODEC, (value, context) -> value.receive(value, context));
        NETWORK.registerS2C(C2SChangeDesktop.TYPE, C2SChangeDesktop.STREAM_CODEC, (value, context) -> value.receive(value, context));
        NETWORK.registerS2C(C2SCancelDesktopChange.TYPE, C2SCancelDesktopChange.STREAM_CODEC, (value, context) -> value.receive(value, context));
        NETWORK.registerS2C(C2SRequestWaypoints.TYPE, C2SRequestWaypoints.STREAM_CODEC, (value, context) -> value.receive(value, context));
        NETWORK.registerS2C(C2STravelToWaypoint.TYPE, C2STravelToWaypoint.STREAM_CODEC, (value, context) -> value.receive(value, context));
        NETWORK.registerS2C(C2SUploadWaypoint.TYPE, C2SUploadWaypoint.STREAM_CODEC, (value, context) -> value.receive(value, context));
        NETWORK.registerS2C(C2SEditWaypoint.TYPE, C2SEditWaypoint.STREAM_CODEC, (value, context) -> value.receive(value, context));
        NETWORK.registerS2C(C2SOpenCoordinatesDisplayMessage.TYPE, C2SOpenCoordinatesDisplayMessage.STREAM_CODEC, (value, context) -> value.receive(value, context));
        NETWORK.registerS2C(C2SOpenEditCoordinatesDisplayMessage.TYPE, C2SOpenEditCoordinatesDisplayMessage.STREAM_CODEC, (value, context) -> value.receive(value, context));
        NETWORK.registerS2C(C2SRemoveWaypointEntry.TYPE, C2SRemoveWaypointEntry.STREAM_CODEC, (value, context) -> value.receive(value, context));
        NETWORK.registerS2C(C2SOpenCoordinatesDisplayMessage.TYPE, C2SOpenCoordinatesDisplayMessage.STREAM_CODEC, (value, context) -> value.receive(value, context));
        NETWORK.registerS2C(C2SUnlockUpgrade.TYPE, C2SUnlockUpgrade.STREAM_CODEC, (value, context) -> value.receive(value, context));
        NETWORK.registerS2C(C2SRequestShellSelection.TYPE, C2SRequestShellSelection.STREAM_CODEC, (value, context) -> value.receive(value, context));
        NETWORK.registerS2C(C2SChangeHum.TYPE, C2SChangeHum.STREAM_CODEC, (value, context) -> value.receive(value, context));
        NETWORK.registerS2C(C2SEjectPlayer.TYPE, C2SEjectPlayer.STREAM_CODEC, (value, context) -> value.receive(value, context));
        NETWORK.registerS2C(C2SExitTardisView.TYPE, C2SExitTardisView.STREAM_CODEC, (value, context) -> value.receive(value, context));
        NETWORK.registerS2C(C2SBeginShellView.TYPE, C2SBeginShellView.STREAM_CODEC, (value, context) -> value.receive(value, context));

    }



}
