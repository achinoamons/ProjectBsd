package primitives;

public class Material {
    public double kD=0;//diffuse
    public double kS=0;//specular
    public int nShininess=0;
    /**
     * setters for this fields
     *
     * @return the object itself-chaining method (like builder pattern)
     */
    public Material setkD(double kD) {
        this.kD = kD;
        return this;
    }

    public Material setkS(double kS) {
        this.kS = kS;
        return this;
    }

    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
