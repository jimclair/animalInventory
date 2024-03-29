/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Animal;
import Model.DataProvider;
import Model.Dog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author james.clair
 */
public class DisplayAnimalController implements Initializable {

	Stage stage;
	Parent scene;
	

   	@FXML
   	private TableView<Animal> animalTableView;
	
	@FXML
        private TableColumn<Animal, Integer> animalIdCol;

        @FXML
        private TableColumn<Animal, Integer> lifespanCol;

        @FXML
        private TableColumn<Animal, String> breedCol;

        @FXML
        private TableColumn<Animal, Double> priceCol;	


        @FXML
        void onActionDisplayAnimalDetails(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/View/AnimalDetailsMenu.fxml"));
		loader.load();

		AnimalDetailsController ADMController = loader.getController();
		ADMController.sendAnimal(animalTableView.getSelectionModel().getSelectedItem());

		
		stage = (Stage)((Button)event.getSource()).getScene().getWindow();
		Parent scene = loader.getRoot();
		stage.setScene(new Scene(scene));
		stage.show();
        }

        @FXML
        void onActionDisplayMainMenu(ActionEvent event) throws IOException {
               
		stage = (Stage)((Button)event.getSource()).getScene().getWindow();
		scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
		stage.setScene(new Scene(scene));
		stage.show();
	}

	public boolean search(int id){
		for(Animal dog : DataProvider.getAllAnimals()) {
			if(dog.getId() == id) {
				return true;
			}
		}
		return false;
	}
	
	public boolean update(int id, Animal animal) {
		int index = -1;

		for(Animal dog : DataProvider.getAllAnimals()) {
			index++;
			if (dog.getId() == id) {
				DataProvider.getAllAnimals().set(index, animal);
				return true;
			}
			
		}
		return false;
	}

	public boolean delete(int id) {
		for (Animal dog : DataProvider.getAllAnimals()) {
			if(dog.getId() == id)
				return DataProvider.getAllAnimals().remove(dog);
		}
		return false;
	}
	public Animal selectAnimal(int id) {
		for(Animal dog :DataProvider.getAllAnimals()) {
			if(dog.getId() == id)
				return dog;
		}
		return null;
	}

	public ObservableList<Animal> filter(String breed) {
		if (!(DataProvider.getAllFilteredAnimals().isEmpty()))
			DataProvider.getAllFilteredAnimals().clear();

		for(Animal dog : DataProvider.getAllAnimals()) {
			if(dog.getBreed().contains(breed))	
				DataProvider.getAllFilteredAnimals().add(dog);
		}

		if(DataProvider.getAllFilteredAnimals().isEmpty())
			return DataProvider.getAllAnimals();
		else
			return DataProvider.getAllFilteredAnimals();

	}
	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		animalTableView.setItems(DataProvider.getAllAnimals());
		//animalTableView.setItems(filter("Al"));
		animalIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		breedCol.setCellValueFactory(new PropertyValueFactory<>("breed"));
		lifespanCol.setCellValueFactory(new PropertyValueFactory<>("lifespan"));
		priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


		/*
		if(search(44))
			System.out.println("Match!");
		else
			System.out.println("No Match!");
		
		if(update(55, new Dog(5, "German Shepherd", 13, "Alert", 1599.99, true, "Gymnast")))
			System.out.println("Update successful!");
		else
			System.out.println("Update failed!");



		if(delete(3))		
			System.out.println("Deleted!");
		else
			System.out.println("No Match!");

		*/

		//animalTableView.getSelectionModel().select(selectAnimal(5));
	}	

	
}
