package br.com.fiap.ecommerce_cart_ms.adapter.ports.outputport;

import br.com.fiap.ecommerce_cart_ms.domain.entities.CartEntity;
import br.com.fiap.ecommerce_cart_ms.mock.ItemEntityMock;
import br.com.fiap.ecommerce_cart_ms.ports.outputport.CartManagementOutputPort;
import br.com.fiap.ecommerce_cart_ms.ports.outputport.ItemManagementOutputPort;
import br.com.fiap.ecommerce_cart_ms.ports.outputport.SessionManagementOutputPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

class CartManagementOutputPortAdapterTest {

  @Mock
  private ItemManagementOutputPort itemManagementOutputPort;

  @Mock
  private SessionManagementOutputPort sessionManagementOutputPort;

  private CartManagementOutputPort cartManagementOutputPort;
  private AutoCloseable openMocks;

  @BeforeEach
  void setup() {

    openMocks = MockitoAnnotations.openMocks(this);
    cartManagementOutputPort = new CartManagementOutputPortAdapter(
            itemManagementOutputPort,
            sessionManagementOutputPort);

  }

  @AfterEach
  void tearDown() throws Exception {

    openMocks.close();

  }

  @Test
  void shouldAddCartItemWithSuccess() {

    //Act
    var response = cartManagementOutputPort.addCartItem(ItemEntityMock.get(), "");

    //Assert
    assertThat(response)
            .isNotNull()
            .isInstanceOf(CartEntity.class);

    assertThat(response.getItems())
            .isNotEmpty();

  }

  @Test
  void shouldRemoveCartItemWithSuccess() {

    //Arrange
    var itemEntity = ItemEntityMock.get();
    var itemEntityId = itemEntity.getId();

    //Act
    var response = cartManagementOutputPort.removeCartItem(itemEntityId, "");

    //Assert
    assertThat(response)
            .isNotNull()
            .isInstanceOf(CartEntity.class);

  }

}