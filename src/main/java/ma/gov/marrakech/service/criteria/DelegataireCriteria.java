package ma.gov.marrakech.service.criteria;


import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the {@link ma.gov.marrakech.domain.Delegataire} entity. This class is used
 * in {@link ma.gov.marrakech.web.rest.DelegataireResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /delegataires?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DelegataireCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;
    private LongFilter id;
    private StringFilter raisonSocial;
    private StringFilter raisoCom;
    private StringFilter idFiscale;
    private StringFilter ice;
    private StringFilter numCnss;
    private StringFilter adresse;
    private StringFilter tel;
    private StringFilter fax;
    private StringFilter email;
    private StringFilter gerant;
    private StringFilter structure;
    private StringFilter capital;

    public DelegataireCriteria(){
    }

    public DelegataireCriteria(DelegataireCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.raisonSocial = other.raisonSocial == null ? null : other.raisonSocial.copy();
        this.raisoCom = other.raisoCom == null ? null : other.raisoCom.copy();
        this.idFiscale = other.idFiscale == null ? null : other.idFiscale.copy();
        this.ice = other.ice == null ? null : other.ice.copy();
        this.numCnss = other.numCnss == null ? null : other.numCnss.copy();
        this.adresse = other.adresse == null ? null : other.adresse.copy();
        this.tel = other.tel == null ? null : other.tel.copy();
        this.fax = other.fax == null ? null : other.fax.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.gerant = other.gerant == null ? null : other.gerant.copy();
        this.structure = other.structure == null ? null : other.structure.copy();
        this.capital = other.capital == null ? null : other.capital.copy();
    }

    @Override
    public DelegataireCriteria copy() {
        return new DelegataireCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }
    public void setId(LongFilter id) {
        this.id = id;
    }
    public StringFilter getRaisonSocial() {
        return raisonSocial;
    }
    public void setRaisonSocial(StringFilter raisonSocial) {
        this.raisonSocial = raisonSocial;
    }
    public StringFilter getRaisoCom() {
        return raisoCom;
    }
    public void setRaisoCom(StringFilter raisoCom) {
        this.raisoCom = raisoCom;
    }
    public StringFilter getIdFiscale() {
        return idFiscale;
    }
    public void setIdFiscale(StringFilter idFiscale) {
        this.idFiscale = idFiscale;
    }
    public StringFilter getIce() {
        return ice;
    }
    public void setIce(StringFilter ice) {
        this.ice = ice;
    }
    public StringFilter getNumCnss() {
        return numCnss;
    }
    public void setNumCnss(StringFilter numCnss) {
        this.numCnss = numCnss;
    }
    public StringFilter getAdresse() {
        return adresse;
    }
    public void setAdresse(StringFilter adresse) {
        this.adresse = adresse;
    }
    public StringFilter getTel() {
        return tel;
    }
    public void setTel(StringFilter tel) {
        this.tel = tel;
    }
    public StringFilter getFax() {
        return fax;
    }
    public void setFax(StringFilter fax) {
        this.fax = fax;
    }
    public StringFilter getEmail() {
        return email;
    }
    public void setEmail(StringFilter email) {
        this.email = email;
    }
    public StringFilter getGerant() {
        return gerant;
    }
    public void setGerant(StringFilter gerant) {
        this.gerant = gerant;
    }
    public StringFilter getStructure() {
        return structure;
    }
    public void setStructure(StringFilter structure) {
        this.structure = structure;
    }
    public StringFilter getCapital() {
        return capital;
    }
    public void setCapital(StringFilter capital) {
        this.capital = capital;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DelegataireCriteria that = (DelegataireCriteria) o;
        return
                Objects.equals(id, that.id) &&
                Objects.equals(raisonSocial, that.raisonSocial) &&
                Objects.equals(raisoCom, that.raisoCom) &&
                Objects.equals(idFiscale, that.idFiscale) &&
                Objects.equals(ice, that.ice) &&
                Objects.equals(numCnss, that.numCnss) &&
                Objects.equals(adresse, that.adresse) &&
                Objects.equals(tel, that.tel) &&
                Objects.equals(fax, that.fax) &&
                Objects.equals(gerant, that.gerant) &&
                Objects.equals(structure, that.structure) &&
                Objects.equals(capital, that.capital);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            raisonSocial,
            raisoCom,
            idFiscale,
            ice,
            numCnss,
            adresse,
            tel,
            fax,
            gerant,
            structure,
            capital
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DelegataireCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (raisonSocial != null ? "raisonSocial=" + raisonSocial + ", " : "") +
            (raisoCom != null ? " raisoCom=" + raisoCom +", " : "") +
            (idFiscale != null ? " idFiscale=" + idFiscale +", " : "") +
            (ice != null ? " ice=" + ice +", " : "") +
            (numCnss != null ? " numCnss=" + numCnss + ", " : "") +
            (adresse != null ? " adresse=" + adresse +  ", " : "") +
            (tel != null ? " tel=" + tel +  ", " : "") +
            (fax != null ? " fax=" + fax +  ", " : "") +
            (email != null ? " email=" + email +  ", " : "") +
            (gerant != null ? " gerant=" + gerant +  ", " : "") +
            (structure != null ? " structure=" + structure +  ", " : "") +
            (capital != null ? " capital=" + capital +  ", " : "") +
            '}';
    }

}
