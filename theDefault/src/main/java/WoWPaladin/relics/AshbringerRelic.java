package WoWPaladin.relics;


import WoWPaladin.WoWPaladin;
import WoWPaladin.orbs.SealOfRighteousness;
import WoWPaladin.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static WoWPaladin.WoWPaladin.makeRelicOutlinePath;
import static WoWPaladin.WoWPaladin.makeRelicPath;

public class AshbringerRelic extends CustomRelic {

    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     *
     * Before combat, channel a Seal of Righteousness
     */

    // ID, images, text.
    public static final String ID = WoWPaladin.makeID("AshbringerRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Ashbringer.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Ashbringer.png"));

    public AshbringerRelic() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    // Flash at the start of Battle.
    @Override
    public void atBattleStartPreDraw() {
        flash();
    }

    public void atPreBattle()
    {
        AbstractDungeon.player.channelOrb(new SealOfRighteousness());
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}

