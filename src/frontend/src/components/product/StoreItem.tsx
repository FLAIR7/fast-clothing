import { FavoriteBorderOutlined, RemoveCircleOutline, ShoppingCartOutlined } from "@material-ui/icons";
import { ProductResponseBody } from "../../types/productTypes";
import { useShoppingCart } from '../../contexts/ShoppingCartContext';
import { formatCurrency } from "../../utils/formatCurrency";
import {
  Container,
  Image,
  Info,
  Icon,
  Text
} from "./StoreItem-styles"

interface Props {
    product: ProductResponseBody
}

export function StoreItem({product}: Props){
    const {
        getItemQuantity, 
        increaseCartQuantity,
        removeFromCart
      } = useShoppingCart()
    const quantity = getItemQuantity(product.productId);
    return (
        <Container>
            <Image src={product.img}/>
            <Info>
              {quantity === 0 ? (
              <Icon>
                <ShoppingCartOutlined onClick={() => increaseCartQuantity(product.productId)}/>
              </Icon>
              ) : (
              <Icon>
                <RemoveCircleOutline onClick={() => removeFromCart(product.productId)}/>
              </Icon>
              )}
              <Icon>
                <FavoriteBorderOutlined/>
              </Icon>
              <Text>{formatCurrency(product.price)}</Text>
            </Info>
        </Container>    
    )
}