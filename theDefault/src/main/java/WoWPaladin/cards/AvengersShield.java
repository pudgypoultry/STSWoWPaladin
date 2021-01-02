package WoWPaladin.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import WoWPaladin.WoWPaladin;
import WoWPaladin.characters.ThePaladin;

import java.util.Iterator;

import static WoWPaladin.WoWPaladin.makeCardPath;

public class AvengersShield extends AbstractDynamicCard {

    /*
     * Avenger's Shield - 1
     * Uncommon Attack
     * Deal 2 damage to a random enemy 3(4) times. Gain 2 block for each enemy in combat.
     */

    // TEXT DECLARATION

    public static final String ID = WoWPaladin.makeID("AvengersShield");
    public static final String IMG = makeCardPath("Attack.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = ThePaladin.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DAMAGE = 2;
    private static final int BLOCK = 2;
    private static final int MAGIC_NUMBER = 3;
    private static final int UPGRADE_MAGIC_NUMBER = 1;

    public int specialDamage;

    // /STAT DECLARATION/

    public AvengersShield() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.damage = baseDamage = DAMAGE;
        this.block = baseBlock = BLOCK;
        this.magicNumber = baseMagicNumber = MAGIC_NUMBER;


        isMultiDamage = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

            for(int i = 0; i < this.magicNumber; ++i) {
                this.addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            }

        Iterator var3 = AbstractDungeon.getMonsters().monsters.iterator();

        while(var3.hasNext()) {
            AbstractMonster monster = (AbstractMonster)var3.next();
            if (!monster.isDead && !monster.isDying) {
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
            }
        }
        }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC_NUMBER);
            initializeDescription();
        }
    }
}