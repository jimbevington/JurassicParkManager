package com.codeclan.jurassicpark.db.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="parks")
public class Park {

    private int id;
    private String name;
    private List<Paddock> paddocks;

    public Park() {
    }

    public Park(String name) {
        this.name = name;       // perhaps we could get rid of this even? Its always Jurassic Park
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "park", fetch = FetchType.LAZY)
    public List<Paddock> getPaddocks() {
        return paddocks;
    }

    public void setPaddocks(List<Paddock> paddocks) {
        this.paddocks = paddocks;
    }


//  public void openPark(){
//      for(Paddock paddock : paddocks){
//          paddock.setVisitors(int);
//      }
//  }

//    public void closePark(){
//        for(Paddock paddock : paddocks){
//            paddock.setVisitors(0);
//        }
//    }

//    public void lockdownPaddock(int paddocknum){
//         for(Paddock paddock : paddocks){
//             if(paddock.getId() == paddocknum){
//                 paddock.setVisitors(0);
//             }
//         }
//    }

//  public void lockdownPark(){
//        for(Paddock paddock : paddocks){
//            if(paddock.getVisitors().size() > 0){
//                paddock.setVisitors(0);
//            }
//        }
//  }

  public void moveDino(int paddockId, Dinosaur dino){
      for(Paddock paddock : paddocks) {
        if(paddock.getId() == paddockId){
            Set<Dinosaur> occupants = new HashSet<Dinosaur>();
            if(occupants.size() == 0){
                occupants.add(dino);
            }else{
                for(Dinosaur dinosaur : occupants){
                    if(dinosaur.getClass() == Herbivore.class && dino.getClass() == Herbivore.class){
                        occupants.add(dino);
                    }else{
                        if(dinosaur.getClass() == Carnivore.class && dino.getSpecies() == dinosaur.getSpecies()){
                            occupants.add(dino);
                        }
                    }
                }
            }
        }
      }
    }

//  public void feedDino(int paddockId, int dinoId){
//        for(Paddock paddock : paddocks){
//            if(paddock.getId() == paddockId){
//                Set<Dinosaur> dinos = paddock.getDinosaurs();
//                for(Dinosaur dinosaur : dinos){
//                    if(dinosaur.getClass() == Carnivore.class && dinosaur.getId() == dinoId){
//                        dinosaur.getFed();
//                    }
//                }
//            }
//        }
//    }


  }

//  public void parkStatus(){}

