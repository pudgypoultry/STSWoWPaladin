package WoWPaladin.cards;

import WoWPaladin.WoWPaladin;
import WoWPaladin.characters.ThePaladin;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.actions.watcher.MeditateAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static WoWPaladin.WoWPaladin.makeCardPath;

public class LightsMeditation extends CustomCard {

    /*
     * Light's Meditation - X
     * Rare Skill
     * Put X(+1) cards from your discard into your hand. Retain them this turn. Exhaust.
     *
     */

    // TEXT DECLARATION

    public static final String ID = WoWPaladin.makeID("LightsMeditation");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("LightsMeditation.png");


    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = ThePaladin.Enums.COLOR_PALADINYELLOW;

    private static final int COST = -1;


    // /STAT DECLARATION/

    public LightsMeditation() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1)
            effect = this.energyOnUse;
        if (p.hasRelic("Chemical X")) {
            effect += 2;
            p.getRelic("Chemical X").flash();
        }
        if(this.upgraded){
            AbstractDungeon.actionManager.addToBottom(new MeditateAction(effect + 1));
        }
        else{
            AbstractDungeon.actionManager.addToBottom(new MeditateAction(effect));
        }

        AbstractDungeon.actionManager.addToBottom(new LoseEnergyAction(effect));
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
