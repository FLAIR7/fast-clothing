import React, { useEffect, useState } from 'react'
import ProductController from '../../controllers/productController'
import { Container, Item } from './Home-styles'
import { ProductResponseBody } from '../../types/productTypes'
import api from '../../services/api'
import axios, { AxiosPromise, AxiosResponse } from 'axios'
import ProductList from '../../components/product/ProductList'

export function Home() {

  const controller = new ProductController();

  return (
      <ProductList controller={controller}/>
  )
}

export default Home