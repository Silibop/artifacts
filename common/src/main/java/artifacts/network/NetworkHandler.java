package artifacts.network;

import artifacts.Artifacts;
import dev.architectury.networking.NetworkChannel;

public class NetworkHandler {

    public static final NetworkChannel CHANNEL = NetworkChannel.create(Artifacts.id("networking_channel"));

    public static void register() {
        CHANNEL.register(BooleanGameRuleChangedPacket.class, BooleanGameRuleChangedPacket::encode, BooleanGameRuleChangedPacket::new, BooleanGameRuleChangedPacket::apply);
        CHANNEL.register(IntegerGameRuleChangedPacket.class, IntegerGameRuleChangedPacket::encode, IntegerGameRuleChangedPacket::new, IntegerGameRuleChangedPacket::apply);
        CHANNEL.register(ToggleArtifactPacket.class, ToggleArtifactPacket::encode, ToggleArtifactPacket::new, ToggleArtifactPacket::apply);
        CHANNEL.register(DoubleJumpPacket.class, DoubleJumpPacket::encode, DoubleJumpPacket::new, DoubleJumpPacket::apply);
        CHANNEL.register(SwimPacket.class, SwimPacket::encode, SwimPacket::new, SwimPacket::apply);
        CHANNEL.register(ChorusTotemUsedPacket.class, ChorusTotemUsedPacket::encode, ChorusTotemUsedPacket::new, ChorusTotemUsedPacket::apply);
        CHANNEL.register(PlaySoundAtPlayerPacket.class, PlaySoundAtPlayerPacket::encode, PlaySoundAtPlayerPacket::new, PlaySoundAtPlayerPacket::apply);
    }
}
