package WoWPaladin.cards;

import WoWPaladin.powers.HolyPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import WoWPaladin.WoWPaladin;
import WoWPaladin.characters.ThePaladin;

import java.util.Iterator;

import static WoWPaladin.WoWPaladin.makeCardPath;

public class EqualizingSwath extends AbstractDynamicCard {

    /*
     * Equalizing Swath - 2
     * Common Attack
     * Deal 18(22) damage divided equally among all enemies. Gain 2 Holy.
     */

    // TEXT DECLARATION

    public static final String ID = WoWPaladin.makeID("EqualizingSwath");
    public static final String IMG = makeCardPath("Attack.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = ThePaladin.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int DAMAGE = 18;
    private static final int UPGRADE_DAMAGE = 8;
    private static final int MAGIC = 2;

    public int specialDamage;

    // /STAT DECLARATION/

    public EqualizingSwath() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.damage = baseDamage = DAMAGE;
        this.magicNumber = baseMagicNumber = MAGIC;


        isMultiDamage = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        Iterator var3 = AbstractDungeon.getMonsters().monsters.iterator();
        float monsterNumber = AbstractDungeon.getMonsters().monsters.size();
        float initialDamage = damage;
        int equalizedDamage = Math.round(initialDamage/monsterNumber);

        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, equalizedDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));

        AbstractDungeon.actionManager.addToBottom(
                new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(
                        AbstractDungeon.player, AbstractDungeon.player, new HolyPower(p, this.magicNumber), this.magicNumber));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_DAMAGE);
            initializeDescription();
        }
    }
}
