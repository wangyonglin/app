package com.khbd.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.vendor.design.Atom;


import java.io.Serializable;
import java.util.List;

public class RecyclerData implements Serializable {
    private List<Atom> atoms;

    public RecyclerData(List<Atom> atoms) {
        this.atoms = atoms;
    }

    public List<Atom> getAtoms() {
        return atoms;
    }

    public void setAtoms(List<Atom> atoms) {
        this.atoms = atoms;
    }
}
